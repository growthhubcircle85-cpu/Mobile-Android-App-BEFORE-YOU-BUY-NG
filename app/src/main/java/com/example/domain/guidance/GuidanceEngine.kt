package com.example.domain.guidance

import com.example.ui.screens.propertycheck.PropertyCheckUiState

data class GuidanceResult(
    val title: String,
    val confidenceSubtitle: String,
    val recommendedNextSteps: List<String>,
    val suggestedProfessionals: List<String>,
    val importantWarnings: List<String>,
    val documentsToRequest: List<String>,
    val officialResources: List<String>
)

fun generateGuidanceResult(state: PropertyCheckUiState): GuidanceResult {
    val warnings = mutableListOf<String>()
    val nextSteps = mutableListOf<String>()
    val professionals = mutableListOf<String>()
    val requestDocs = mutableListOf<String>()
    val agencies = mutableListOf<String>()

    // 1. Seller-Type Specific Logic
    when (state.sellerType) {
        "Family" -> {
            warnings.add("URGENT WARNING: Under customary law, family land belongs to the family as a corporate entity. Selling family land without the joint consent of the designated family heads is legally invalid. Ensure you obtain joint signatures from verified heads of family and conduct independent surveyor boundary validation.")
            nextSteps.add("Insist on meeting the prominent head of the family ('Olori Ebi') and at least two other recognized senior family members synchronously.")
            nextSteps.add("Ensure any eventual payment is made to a designated, collective family bank escrow account rather than an individual's private account.")
            professionals.add("Property Lawyer (absolutely critical to draft joint covenants)")
            professionals.add("Registered Surveyor (to confirm boundary markers are not contested by neighbors)")
        }
        "Agent" -> {
            warnings.add("WARNING: Middlemen and agents are paid on transaction volume. They bear zero structural liability for ownership or document fraud that emerges years from now. Always verify third-party agency validation documentation and never make payments directly into personal agent accounts.")
            nextSteps.add("Request the formal contact details of the primary owner and verify that the agent has a signed, written Power of Attorney permitting them to execute checks.")
            nextSteps.add("Refuse paying any cash or commission to the agent until the root title is confirmed by your independent legal counsel.")
            professionals.add("Independent Property Lawyer (working solely on your behalf)")
        }
        "Developer", "Company" -> {
            warnings.add("WARNING: Developers often market projects as 'fully secured' with ongoing 'excision processing'. This means the land is still legally under state acquisition until excision is gazetted.")
            nextSteps.add("Demand to see the developer's registered layout plan approved by the state physical planning authority.")
            nextSteps.add("Check the developers' company registration details at the Corporate Affairs Commission (CAC).")
            professionals.add("Independent Lawyer familiar with corporate real estate contracts")
            professionals.add("Building Surveyor / Architect (to inspect structural stages before installment payments)")
        }
        else -> { // "Individual"
            warnings.add("Confirm that the individual seller is indeed the person named on the registered land certificates, and that they have the legal capacity to sell.")
            nextSteps.add("Request a valid, government-issued identification from the seller to crosscheck against the property title papers.")
            professionals.add("Property Lawyer")
        }
    }

    // 2. Property Type Specific Logic
    when (state.selectedPropertyType) {
        "Off-plan Property" -> {
            warnings.add("Off-plan properties are vulnerable to extended construction delays, bankruptcy, double-allocation, or developers shifting layouts without consent. Require documentation verification for building approvals, strict default/refund penalty terms, and allocation timeline enforcement.")
            nextSteps.add("Insist on a written Contract of Sale containing precise clauses regarding delivery timelines, materials, and comprehensive cash refund triggers.")
            requestDocs.addAll(listOf("Building Plan Approval", "Contract of Sale with default clauses", "Stage inspection progress certificates", "Title Document (C of O / Governor's Consent)"))
        }
        "Estate Property" -> {
            warnings.add("Estates often enforce high, unquoted annual levies, service charges, physical building restrictions, and compulsory development timelines.")
            nextSteps.add("Request the official Estate Code of Conduct and a breakdown of security, waste, and community association service fees.")
            requestDocs.addAll(listOf("Global C of O of the Estate", "Individual allocation terms", "Approved layout plan showing your selected plot number"))
        }
        "Land" -> {
            warnings.add("Every state operates custom agricultural, forestry, and industrial zoning. Raw land is highly vulnerable to boundary creep, double allocation, and local overlapping claims.")
            nextSteps.add("Do not place a wooden fence or structure on the land without first clearing boundary beacons.")
            professionals.add("Registered Surveyor (Mandatory to check boundary markers on-site)")
            requestDocs.add("Registered Survey Plan with official beacon numbers")
        }
        "House" -> {
            warnings.add("Existing buildings may have severe structural defects masked by cosmetic painting or quick remodeling.")
            nextSteps.add("Perform a comprehensive daylight structural and dampness audit on the property.")
            professionals.add("Structural Engineer or Independent Architect (to inspect foundation cracks and structural health)")
            requestDocs.addAll(listOf("Approved building plans", "Certificate of occupancy", "Deed of transfer from previous owner"))
        }
    }

    // 3. Document Search & Empty Document Advisories
    if (state.availableDocuments.contains("None / Not Sure") || state.availableDocuments.isEmpty()) {
        warnings.add("CRITICAL ALERT: The seller has not provided, or is not sure of, any formal land documents. Pause any transaction processing immediately and request formal documentation. Buying property under receipt-only or verbal agreement carries a near-total probability of title loss or boundary dispute.")
        nextSteps.add("PAUSE ALL TRANSACTIONS IMMEDIATELY. Tell the seller that your family clearance board prohibits any cash transfer until clear, verifiable land certificates are presented.")
    } else {
        var missingCritical = false
        if (!state.availableDocuments.contains("Survey Plan") && state.selectedPropertyType == "Land") {
            warnings.add("Missing Survey Plan: Without a registered boundary plan, any coordinate checks at the Lands Bureau are physically impossible.")
            missingCritical = true
        }
        if (!state.availableDocuments.contains("C of O") && !state.availableDocuments.contains("Governor's Consent") && !state.availableDocuments.contains("Gazette")) {
            warnings.add("Missing Root Title: The available items do not demonstrate a clear recognized root of title from the state. Be extremely cautious.")
            missingCritical = true
        }

        if (missingCritical) {
            nextSteps.add("Explicitly write to the seller requesting clear, scanned PDF copies of missing documents so your surveyor can proceed.")
        } else {
            nextSteps.add("Verify the provided documents through the regional Land Registry of the Ministry of Lands.")
        }
    }

    // 4. Location Context
    when (state.location) {
        "Lagos" -> {
            agencies.add("Lagos Lands Bureau (Alausa, Ikeja)")
            agencies.add("LASRERA (Lagos Real Estate Regulatory Authority)")
            nextSteps.add("Perform a coordinate verification check at the Lagos State Surveyor General in Alausa to ensure the land does not fall within a committed government layout/forest reserve.")
        }
        "Abuja" -> {
            agencies.add("FCT Department of Land Administration")
            agencies.add("AGIS (Abuja Geographic Information Systems)")
            nextSteps.add("Request a certified AGIS Search Report. Do not pay any purchase fee until AGIS verifies the allocation number and details.")
        }
        else -> {
            agencies.add("State Ministry of Lands / Local Land Registry")
            agencies.add("Office of the State Surveyor-General")
            nextSteps.add("Consult with a local attorney residing physically in the county/state capital of the property location, who understands state land registry practices.")
        }
    }

    // 5. Diaspora / Abroad Logic
    if (state.isDiaspora) {
        warnings.add("DIASPORA WARNING: Diaspora checks are highly vulnerable to proxy fraud. Family members or close agents in Nigeria often overestimate their due diligence, purchase unverified tracts, or inflate site costs. Appoint completely independent trusted physical inspectors or localized legal representatives rather than relying purely on digital media packages.")
        nextSteps.add("Appoint an independent, external professional who has zero personal or kinship connection to the seller or your proxies.")
        nextSteps.add("Never rely on WhatsApp pictures or scanned copies alone. Always have an independent surveyor physically verify coordinates on the ground.")
    }

    // 6. Urgency High Pressure Logic
    if (state.isUrgentPressure) {
        warnings.add("URGENT PRESSURE ALERT: Artificial urgency is a common mechanism used to bypass transaction scrutiny. Halt payment processing immediately until full due diligence clears.")
        nextSteps.add("Instantly state to the seller: 'My financial clearing process has a mandatory 7-working-day review cycle.'")
    }

    // Fallback placeholders if lists are empty
    if (professionals.isEmpty()) {
        professionals.add("Property Lawyer licensed in Nigeria")
    }
    if (requestDocs.isEmpty()) {
        requestDocs.addAll(listOf("Registered Survey Plan", "Deed of Assignment", "Root of Title (C of O / Gazette)"))
    }

    return GuidanceResult(
        title = "Personalized Property Assessment Guidance",
        confidenceSubtitle = "Your customized advisory report based on current selections",
        recommendedNextSteps = nextSteps.distinct(),
        suggestedProfessionals = professionals.distinct(),
        importantWarnings = warnings.distinct(),
        documentsToRequest = requestDocs.distinct(),
        officialResources = agencies.distinct(),
    )
}
