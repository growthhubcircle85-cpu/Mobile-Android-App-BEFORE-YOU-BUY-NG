package com.example.data.staticcontent

// Data models for the Before You Buy NG app

data class RedFlag(
    val id: String,
    val title: String,
    val shortSummary: String,
    val explanation: String,
    val actionSteps: List<String>
)

data class DocumentExplanation(
    val title: String,
    val description: String,
    val legalSignificance: String,
    val verificationChannel: String
)

data class RegionalGuide(
    val title: String,
    val subtitle: String,
    val contextText: String,
    val commonDocuments: List<String>,
    val warnings: List<String>,
    val criticalAdvice: String
)

data class ResourceLink(
    val title: String,
    val description: String,
    val url: String?, // null if "Official link being verified"
    val agency: String
)

data class ArticleContent(
    val title: String,
    val subtitle: String,
    val author: String,
    val publishDate: String,
    val readTime: String,
    val contentBlocks: List<String>,
    val pullQuotes: List<String>,
    val relatedGuidance: List<String>
)

// Main Static Library
object StaticData {

    // First Foundational Article
    val foundationalArticle = ArticleContent(
        title = "Buying Land From Family Representatives",
        subtitle = "A Calm Practical Guide Before You Pay",
        author = "Civic Protection Editorial Team",
        publishDate = "May 2026",
        readTime = "6 min read",
        contentBlocks = listOf(
            "Land sales involving family lands in Nigeria—frequently classified as 'Ancestral Land' or 'Family Land'—are among the most common sources of long-running property litigation in Nigerian courts. Under customary law across most states, family land belongs to the family as a corporate entity. No single family member, not even the eldest son or a prominent chief, has the legal right to unilaterally dispose of family property without the joint consent of the lawful family heads.",
            "Often, buyers are approached by a single charismatic individual representing themselves as the sole owner or authorized agent of the family. They might present family receipts or even a signed letter. However, if other factions of the family are not aware or have not consented, the transaction can be declared null and void from the very beginning. The money paid is frequently lost instantly, as the sellers may distribute it quickly, leaving the buyer with no asset and an expensive legal battle.",
            "To protect yourself, you must first slow down and demand a meeting with the principal members of the family, particularly the head of the family and other community leaders. Furthermore, any transaction on family land must be documented in a Deed of Conveyance or Deed of Assignment that is executed by all the authorized representatives—not just one.",
            "Do not rely solely on the word of local middlemen who say 'everything is fine.' Retain your own independent, licensed surveyor to measure the boundaries and run a coordinate check with the regional Surveyor General's office. This ensures the land hasn't already been sold or committed to government acquisition.",
            "Remember: A family land dispute is incredibly difficult to remedy once money changes hands. If there is any internal pushback, warnings from neighbors, or signs of dual claims, completely pause the transaction. The peace of mind of saving your capital is always worth the delay."
        ),
        pullQuotes = listOf(
            "Under customary law, family land belongs to the family as a unified corporate entity. No individual can unilaterally sell it.",
            "Urgency is the enemy of safety. Every day you delay payment to verify family consensus is a day you protect your household savings."
        ),
        relatedGuidance = listOf(
            "Ask to meet at least three principal family heads synchronously, not separately.",
            "Require that the payment be made to a joint family account or escrow supervised by an independent lawyer, rather than a personal account of a single contact.",
            "Look for physical signs of dispute, including active farming by unrecognized parties, old foundation walls, or neighborhood hand-written notices like 'This Land is NOT for Sale' on adjacent walls."
        )
    )

    // Common Red Flags List
    val redFlags = listOf(
        RedFlag(
            id = "no_survey",
            title = "No Survey Plan Available",
            shortSummary = "Seller cannot provide a clear survey plan, or claims it will come later.",
            explanation = "A survey plan is the definitive map of the land. Without it, you cannot verify if the land falls under government acquisition, is zoned for agricultural versus residential use, or even exists at the coordinates described. Waiting to check this until 'after payment' is a recipe for total capital loss.",
            actionSteps = listOf(
                "Do not make any deposit based of a promise of a future plan.",
                "Demand the coordinates (beacons) of the land from the seller.",
                "Hire an independent, registered surveyor to carry out a coordinate check at the Lands Bureau."
            )
        ),
        RedFlag(
            id = "family_rep_alone",
            title = "Family Representative Acting Alone",
            shortSummary = "One family member attempting to sell land without broader family involvement.",
            explanation = "Customary land ownership requires the consensus of major family nodes/principal representatives. Single-member transactions are frequently overridden by other family branches, leading to instant disputes, lockouts, or court injunctions.",
            actionSteps = listOf(
                "Verify the designated family head (Olori Ebi or equivalent).",
                "Ensure multiple principal representatives sign the Deed of Assignment.",
                "Obtain independent local intelligence from neighboring landowners."
            )
        ),
        RedFlag(
            id = "too_cheap",
            title = "Too Cheap for the Area",
            shortSummary = "Price far below nearby properties may hide a severe title or local dispute.",
            explanation = "If land in a specific layout sells for ₦10,000,000 and you are offered a plot for ₦3,500,000, there is a systemic cause. It usually means the seller knows an eviction, legal dispute, or government acquisition is imminent, or they are executing a double-sale scam.",
            actionSteps = listOf(
                "Analyze prices of multiple plots within a 500-meter radius.",
                "Search government registries rigorously for any pending litigation or caveated files.",
                "Slow down. Let the seller know you need 10 days for inquiries. Watch if they become highly aggressive."
            )
        ),
        RedFlag(
            id = "extreme_pressure",
            title = "Pressure to Pay Immediately",
            shortSummary = "Artificial urgency designed to discourage proper professional searches.",
            explanation = "Sellers create a sense of scarcity ('another buyer is cash-ready right now') so your system enters high-arousal panic. Under stress, your brain skips legal review and coordinate verification. Genuine deals can afford a professional due diligence buffer.",
            actionSteps = listOf(
                "Refuse to make 'commitment fees' or holding deposits under pressure.",
                "Establish that your household policy requires a standard 14-day clearance process.",
                "If the seller claims they cannot wait even 3 days for a lawyer, walk away immediately."
            )
        ),
        RedFlag(
            id = "agent_says_no_lawyer",
            title = "Agent Claims Lawyer Is Not Necessary",
            shortSummary = "Middlemen claiming 'standard agency templates' are enough to protect you.",
            explanation = "An agent's economic incentive is to close the deal and gather commission. They do not carry structural liability for a bad title 5 years from now. A lawyer is legally bound to act in your fiduciary interests.",
            actionSteps = listOf(
                "Never use the seller's or the agent's recommended lawyer exclusively.",
                "Hire an independent legal counsel representing only your side.",
                "Ensure your lawyer reviews the root status of the title documents before any funds leave your account."
            )
        ),
        RedFlag(
            id = "receipt_only",
            title = "Receipt Only Transaction",
            shortSummary = "Proceeding without a formal Deed of Assignment or proper land transfer records.",
            explanation = "A standard purchase receipt is of low value in terms of modern property registration or public notice. It is only evidence that cash changed hands. It does not convey formal proprietary interest or trigger a registered root of title.",
            actionSteps = listOf(
                "Require a legally drafted Contract of Sale and Deed of Assignment.",
                "Insist on immediate execution of transfer covenants under official witness.",
                "Avoid cash transactions; use bank transfers with unambiguous reference codes matching the specific plot coordinates."
            )
        )
    )

    // Document Glossary
    val documents = listOf(
        DocumentExplanation(
            title = "Survey Plan",
            description = "A plan showing the exact boundary measurements, beacon numbers, shapes, and location coordinates.",
            legalSignificance = "It is the primary map used by surveyors to ensure your plot does not overlap with any public roads, military buffers, or government-acquired zones.",
            verificationChannel = "Verify by physically taking a registered surveyor to the land to record GPS coordinates, then conduct a search at the State Surveyor-General's Office."
        ),
        DocumentExplanation(
            title = "Certificate of Occupancy (C of O)",
            description = "The ultimate land document issued directly by the state government, granting a 99-year leasehold.",
            legalSignificance = "Confirms that the government recognizes the specified owner for the stated lease term. However, it will not prevent boundary disputes if the land is not properly surveyed.",
            verificationChannel = "Check the Land Registry/Lands Bureau database to confirm its authenticity, date of issue, and that no active mortgages or caveats are registered against it."
        ),
        DocumentExplanation(
            title = "Governor's Consent",
            description = "Formal approval given by a State Governor consenting to the transfer or transfer-back of a C of O to a new buyer.",
            legalSignificance = "Whenever a land with a C of O is sold, the transaction is legally incomplete until the Governor signs off on this consent. Failing to secure consent leaves you vulnerable.",
            verificationChannel = "Initiated and tracked through the Lands Bureau. Can take several months, which is why payments must be staged safely via professional escrow."
        ),
        DocumentExplanation(
            title = "Gazette",
            description = "An official government record book listing land parcels that have been formally excised (released) back to customary communities.",
            legalSignificance = "Confirms that the land inside the published coordinates belongs to the community rather than the government, making it safe to buy.",
            verificationChannel = "Cross-reference the pages of the state gazette at the Ministry of Information or Lands Bureau against your specific survey coordinates."
        ),
        DocumentExplanation(
            title = "Excision",
            description = "The administrative process where the state government releases a portion of land to indigenous communities.",
            legalSignificance = "Without completed excision (evidenced by a Gazette entry), the land is legally under government acquisition. Your purchase represents a major risk.",
            verificationChannel = "Check coordinates with the Lands Bureau to verify if the general layout falls within the boundaries of a complete, officially excised territory."
        ),
        DocumentExplanation(
            title = "Deed of Assignment",
            description = "The contract transferring property ownership from the seller to you.",
            legalSignificance = "This is your primary evidence of buying the property and must be drawn up, signed by both parties, and officially witnessed under legal seal.",
            verificationChannel = "Must be drafted by a lawyer and subsequently filed, stamped, and registered at the State Land Registry."
        )
    )

    // Regional Guides
    val regions = listOf(
        RegionalGuide(
            title = "Lagos Guidance",
            subtitle = "Slowing down in Nigeria's most complex property ecosystem",
            contextText = "Lagos is infamous for rapid transactions, high property values, and dense layerings of agents. Hidden fees (family development fees, community levies, foundation fees) are extensive and must be factored into your budget upfront.",
            commonDocuments = listOf("Governor's Consent", "Registered Survey Plan", "C of O", "Deed of Assignment"),
            warnings = listOf(
                "Be extremely careful of claims regarding 'excision in progress.' If an excision is not fully gazetted, the land remains government property.",
                "Beware of developer marketing material that says 'C of O title.' Always ask to see the original document number and verify it yourself at Alausa.",
                "Beware of local youths ('Omo-Onile') demanding unauthorized foundation fees."
            ),
            criticalAdvice = "Never make an immediate bank transfer because an agent says 'the price is going up next week.' Under Lagos land law, once you pay a fraudulent agent, recovering funds through standard police channels can be extremely prolonged."
        ),
        RegionalGuide(
            title = "Abuja Guidance",
            subtitle = "Navigating FCT Land Administration and AGIS",
            contextText = "Abuja property transactions are organized under the Abuja Geographic Information Systems (AGIS) and the FCT Department of Land Administration. The system is structurally centralized, making precise searches easier but require careful formal processes.",
            commonDocuments = listOf("Allocation Letter", "FCT Certificate of Occupancy", "AGIS Search Report"),
            warnings = listOf(
                "Informal allocations or buy-ins on raw land in outer municipal areas (Gwagwalada, Kuje, Karu) are frequently unregistered and carry massive risks of double-allocation.",
                "Ensure that any transfer is cleared through AGIS to avoid forged certificates."
            ),
            criticalAdvice = "Ensure you request an official AGIS Search Report before making any payment. A verbal assurance from an FCT clerk is not a legal guarantee."
        ),
        RegionalGuide(
            title = "Nationwide Guidance",
            subtitle = "Safe property decisions across other Nigerian states",
            contextText = "For outer states, property habits depend extensively on localized customary frameworks. Local land registries and state Surveyor-General offices remain the primary sources of truth.",
            commonDocuments = listOf("Survey Plan", "Deed of Conveyance", "State C of O"),
            warnings = listOf(
                "Do not assume because a property is quiet and rural that it has no underlying owner. Boundary disputes are passed down across generations.",
                "Verify zoning laws. What is sold as a commercial highway plot may be strictly zoned for agriculture."
            ),
            criticalAdvice = "Always hire an independent local lawyer who is situated directly inside the state where the land is located. A lawyer in Lagos may not understand the specific customary land systems in Enugu, Rivers, or Kano."
        )
    )

    // Directory of Public / Civic Resources
    val resources = listOf(
        ResourceLink(
            agency = "Lagos Lands Bureau",
            title = "Lagos State Lands Bureau",
            description = "The central office responsible for land records, certificates, and consent processing in Lagos State (based in Ikeja).",
            url = "https://lands.lagosstate.gov.ng"
        ),
        ResourceLink(
            agency = "LASRERA",
            title = "Lagos State Real Estate Regulatory Authority",
            description = "Registry of accredited real estate developers, agents, and organizations operating in Lagos State to curb housing scams.",
            url = "https://lasrera.lagosstate.gov.ng"
        ),
        ResourceLink(
            agency = "AGIS",
            title = "Abuja Geographic Information Systems",
            description = "The official electronic platform for verifying titles, boundary maps, and allocations in the Federal Capital Territory.",
            url = "https://agis.fct.gov.ng"
        ),
        ResourceLink(
            agency = "FCT Land Admin",
            title = "FCT Department of Land Administration",
            description = "The governing agency for allocating and managing public land plots within federal territory districts.",
            url = null // Official link being verified
        )
    )
}

// Assessment Questionnaire State Engine

data class PropertyCheckSelections(
    val propertyType: String = "",       // Land, House, Estate Property, Off-plan Property
    val location: String = "",           // Lagos, Abuja, Other State
    val sellerType: String = "",         // Individual, Family, Company, Developer, Agent
    val availableDocs: Set<String> = emptySet(), // Survey Plan, C of O, Governor’s Consent, Gazette, Excision, Deed of Assignment, Allocation Letter, None / Not Sure
    val buyingFromAbroad: String = "",   // Yes, No
    val extremePressure: String = ""    // Yes, No
)

data class FormStep(
    val stepIndex: Int,
    val questionTitle: String,
    val supportingText: String,
    val options: List<String> = emptyList(),
    val isCheckbox: Boolean = false
)

object Questionnaire {
    val steps = listOf(
        FormStep(
            stepIndex = 1,
            questionTitle = "What are you buying?",
            supportingText = "Property type determines the critical regulatory approvals you need to see.",
            options = listOf("Land", "House", "Estate Property", "Off-plan Property")
        ),
        FormStep(
            stepIndex = 2,
            questionTitle = "Where is the property located?",
            supportingText = "Land administration, official verification channels, and search portals vary significantly by state.",
            options = listOf("Lagos", "Abuja", "Other State")
        ),
        FormStep(
            stepIndex = 3,
            questionTitle = "Who is selling the property?",
            supportingText = "The nature of the seller outlines the legal authority and consensus checks you must perform.",
            options = listOf("Individual", "Family", "Company", "Developer", "Agent")
        ),
        FormStep(
            stepIndex = 4,
            questionTitle = "Which documents are currently available?",
            supportingText = "Select all documents the seller has explicitly shown or provided copies of. (Select all that apply)",
            options = listOf(
                "Survey Plan",
                "C of O",
                "Governor’s Consent",
                "Gazette",
                "Excision",
                "Deed of Assignment",
                "Allocation Letter",
                "None / Not Sure"
            ),
            isCheckbox = true
        ),
        FormStep(
            stepIndex = 5,
            questionTitle = "Are you buying from abroad?",
            supportingText = "Diaspora buyers are targeted with bespoke real estate marketing and suffer high levels of proxy fraud.",
            options = listOf("Yes", "No")
        ),
        FormStep(
            stepIndex = 6,
            questionTitle = "Are you being pressured to pay urgently?",
            supportingText = "Scam loops rely on high-pressure psychological environments to speed up transactions before verification.",
            options = listOf("Yes", "No")
        )
    )
}

// Guidance Result Generator
data class GuidanceResult(
    val title: String,
    val confidenceSubtitle: String,
    val recommendedNextSteps: List<String>,
    val suggestedProfessionals: List<String>,
    val importantWarnings: List<String>,
    val documentsToRequest: List<String>,
    val officialResources: List<String>
)

object GuidanceEvaluator {
    fun evaluate(selections: PropertyCheckSelections): GuidanceResult {
        val warnings = mutableListOf<String>()
        val nextSteps = mutableListOf<String>()
        val professionals = mutableListOf<String>()
        val requestDocs = mutableListOf<String>()
        val agencies = mutableListOf<String>()

        // 1. Seller-Type Specific Logic
        when (selections.sellerType) {
            "Family" -> {
                warnings.add("Under customary law, family land belongs to the family as a corporate entity. Selling family land without the joint consent of the designated family heads is legally invalid.")
                warnings.add("Watch out for internal family disputes or individual siblings attempting to sell land and collect personal funds without broader consensus.")
                nextSteps.add("Insist on meeting the prominent head of the family ('Olori Ebi') and at least two other recognized senior family members.")
                nextSteps.add("Ensure any eventual payment is made to a designated, collective family bank escrow account rather than an individual's private account.")
                professionals.add("Property Lawyer (absolutely critical to draft joint covenants)")
                professionals.add("Registered Surveyor (to confirm boundary markers are not contested by neighbors)")
            }
            "Agent" -> {
                warnings.add("Middlemen and agents are paid on transaction volume. They bear zero structural liability for ownership or document fraud that emerges years from now.")
                warnings.add("Never rely exclusively on verbal assurances or documents sent via messaging apps without doing independent coordinate searches.")
                nextSteps.add("Request the formal contact details of the primary owner and verify that the agent has a signed, written Power of Attorney permitting them to execute checks.")
                nextSteps.add("Refuse of paying any cash or commission to the agent until the root title is confirmed by your independent legal counsel.")
                professionals.add("Independent Property Lawyer (working solely on your behalf)")
                if (selections.propertyType == "Land") {
                    professionals.add("Registered Land Surveyor")
                }
            }
            "Developer", "Company" -> {
                warnings.add("Developers often market projects as 'fully secured' with ongoing 'excision processing'. This means the land is still legally under state acquisition until excision is gazetted.")
                warnings.add("Off-plan promises are vulnerable to extended construction delays, bankruptcy, double-allocation, or developers shifting layouts without consent.")
                nextSteps.add("Demand to see the developer's registered layout plan approved by the state physical planning authority.")
                nextSteps.add("Check the developers' company registration details at the Corporate Affairs Commission (CAC).")
                professionals.add("Independent Lawyer familiar with corporate real estate contracts")
                professionals.add("Building Surveyor / Architect (to inspect structural stages before installment payments)")
            }
            else -> {
                // Individual
                warnings.add("Confirm that the individual seller is indeed the person named on the registered land certificates, and that they have the legal capacity to sell.")
                warnings.add("Ensure there are no issues involving inheritances, divorces, or unpaid corporate loans caveating the title.")
                nextSteps.add("Request a valid, government-issued identification from the seller to crosscheck against the property title papers.")
                professionals.add("Property Lawyer")
            }
        }

        // 2. Property Type Specific Logic
        when (selections.propertyType) {
            "Off-plan Property" -> {
                warnings.add("Never purchase off-plan properties relying only on glossy marketing brochures. Check actual physical building progress on-site.")
                warnings.add("A lot of off-plan units lack formal building approvals from local municipal boards (e.g., LASBCA in Lagos), leaving them open to demolition.")
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
        if (selections.availableDocs.contains("None / Not Sure") || selections.availableDocs.isEmpty()) {
            warnings.add("CRITICAL WARNING: The seller has not provided, or is not sure of, any formal land documents. Buying property under receipt-only or verbal agreement carries a near-total probability of title loss or boundary dispute.")
            nextSteps.add("PAUSE ALL TRANSACTIONS IMMEDIATELY. Tell the seller that your family clearance board prohibits any cash transfer until clear, verifiable land certificates are presented.")
            nextSteps.add("Refuse representation by the seller's attorney of 'getting documents ready' later.")
        } else {
            // Document checks
            var missingCritical = false
            if (!selections.availableDocs.contains("Survey Plan") && selections.propertyType == "Land") {
                warnings.add("Missing Survey Plan: Without a registered boundary plan, any coordinate checks at the Lands Bureau are physically impossible.")
                missingCritical = true
            }
            if (!selections.availableDocs.contains("C of O") && !selections.availableDocs.contains("Governor’s Consent") && !selections.availableDocs.contains("Gazette")) {
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
        when (selections.location) {
            "Lagos" -> {
                agencies.add("Lagos Lands Bureau (Alausa, Ikeja) for land verification searches.")
                agencies.add("LASRERA to check if the developer or real estate organization is formally registered and licensed.")
                nextSteps.add("Perform a coordinate verification check at the Lagos State Surveyor General in Alausa to ensure the land does not fall within a committed government layout/forest reserve.")
            }
            "Abuja" -> {
                agencies.add("Abuja Geographic Information Systems (AGIS) in Garki, Abuja for FCT land searches.")
                agencies.add("FCT Department of Land Administration.")
                nextSteps.add("Request a certified AGIS Search Report. Do not pay any purchase fee until AGIS verifies the allocation number and details.")
            }
            else -> {
                // Other state
                agencies.add("State Ministry of Lands / Local Land Registry.")
                agencies.add("Office of the State Surveyor-General.")
                nextSteps.add("Consult with a local attorney residing physically in the county/state capital of the property location, who understands state land registry practices.")
            }
        }

        // 5. Diaspora / Abroad Logic
        if (selections.buyingFromAbroad == "Yes") {
            warnings.add("Diaspora checks are highly vulnerable to proxy fraud. Family members or close agents in Nigeria often overestimate their due diligence, purchase unverified tracts, or inflate site costs.")
            nextSteps.add("Appoint an independent, external professional who has zero personal or kinship connection to the seller or your proxies.")
            nextSteps.add("Never rely on WhatsApp pictures or scanned copies alone. Always have an independent surveyor physically verify coordinates on the ground.")
            nextSteps.add("Ensure payments are made exclusively through regulated bank accounts, never through proxy cash handovers.")
        }

        // 6. Urgency High Pressure Logic
        if (selections.extremePressure == "Yes") {
            warnings.add("HIGH ALERT: Urgent sales are the primary vehicle for land fraud. Scammers create a false window of 'immediate payment required' to prevent you from conducting a 3-day search at the land registry.")
            nextSteps.add("Instantly state to the seller: 'My financial clearing process has a mandatory 7-working-day review cycle.'")
            nextSteps.add("If the seller claims they will sell to someone else within 24 hours, let it go. Protecting your hard-earned capital is worth missing any 'urgent bargain'.")
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
}
